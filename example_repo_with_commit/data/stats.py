import sys
import MySQLdb
import getpass

UNIQUE = 0
LEVEL = 0
FIN = None

# 定义树节点
class NODE:
	def __init__(self, value, original):
		self.left = None
		self.right = None
		self.val = value	#Binary value
		self.orig = original	#Original value
		self.count = 1

# 定义树, 这好像是一个集合
class TREE:
	def __init__(self):
		self.root = None
	def getRoot(self):
		return self.root
	def add(self, value, original):
		global UNIQUE
		if(self.root == None):
			self.root = NODE(value, original)
			UNIQUE += 1
		else:
			self._add(value, self.root, original)
	def _add(self, value, node, original):
		global UNIQUE
		if(value < node.val):
			if(node.left is not None):
				self._add(value, node.left, original)
			else:
				node.left = NODE(value, original)
				UNIQUE += 1
		if(value > node.val):
			if(node.right is not None):
				self._add(value, node.right, original)
			else:
				node.right = NODE(value, original)
				UNIQUE += 1
		if(value == node.val):
			node.count = node.count + 1
	def printTREE(self, count, fin):
		global FIN
		FIN = open(fin, 'w')
		FIN.write('Frequency\tValue\n')
		if(self.root is not None):
			self._printTREE(self.root, count)
		FIN.close()
	
	def _printTREE(self, node, count):
		if(node is not None):
			self._printTREE(node.left, count)
			stat = float(node.count)/float(count)
			FIN.write('%.8f'%(stat) + '\t' + str('node val') + '\n')
			self._printTREE(node.right, count)

#Connect to the database as the root user
def init_db():
	pw = getpass.getpass('Database Password: ')
	db = MySQLdb.connect(host='127.0.0.1', user='root', passwd=pw)
	return db

#Display options to user, and prompt for a selection
def show_results(cursor, sql):
	global LEVEL # 管理用户目前操作到哪一层？
	count = 0 # 这是数啥
	option = [] # 存放操作字符串
	cursor.execute(sql) # 执行sql语句
	result = cursor.fetchall() # 接收全部的返回结果行

	#Database menu doesn't have a go back option
	if LEVEL != 0: # 如果用户不是在最顶层
		option.insert(count, '..') # 插入一个数字和..，什么鬼
		print str(count) + ':\t' + option[count] # 然后又输出一下？
		count += 1 # 所以count是统计用户操作数吗

	# 打印操作
	for row in result:
		option.insert(count, row[0])
		print str(count) + ':\t' + option[count]
		count += 1
	while (True):
		ind = raw_input('==========================\nSelection [num]: ')
		if (int(ind) < count and int(ind) >= 0): # 检测非法操作
			break
		print 'Invalid option'
	# Handle next level, and return option chosen
	if (ind == '0' and LEVEL != 0): # ind是操作数
		LEVEL -= 1
		return None
	else:
		LEVEL += 1
		return option[int(ind)]

def main(): # 实现了一个简单的交互
	global UNIQUE # 节点数量？
	global LEVEL
	data = []
	db = init_db() # 以root用户身份连接到数据库
	cursor = db.cursor() # 获得一个cursor对象来对数据库进行操作

	# Allow user to choose different tables and columns from
	# the database until they no longer want to continue
	while True:
		#<LEVEL 0>
		#Allow user to select database
		if LEVEL == 0:
			print '==========================\n'
			sql = 'SHOW DATABASES'
			database = show_results(cursor, sql) # 现在level==0
			sql = 'USE ' + database # database是用户给定的数据库
			cursor.execute(sql)
		#<LEVEL 1>
		#Allow user to select table
		if LEVEL == 1:
			print '==========================\n'
			sql = 'SHOW TABLES'
			table = show_results(cursor, sql) # cursor是一个cursor对象，sql是一个字符串
		#<LEVEL 2>
		# describe table
		if LEVEL == 2:
			print '==========================\n'
			sql = 'DESCRIBE ' + table
			attribute = show_results(cursor, sql) # 表示表中有什么属性
		#<LEVEL 3>
		#Analyze data from chosen attribute
		if LEVEL == 3: 
			LEVEL -= 1	#decrease level to print attributes in next loop
			sql = 'SELECT ' + attribute + ' FROM ' + table # 查看某属性
			if (cursor.execute(sql) < 1): # 找不到该属性
				print 'No rows in this table'
				continue
			result = cursor.fetchall() # 接收全部的返回结果行
			tree = TREE() # 搞棵树
			count = 0
			print '==========================\n'

			# Build tree from query results
			for row in result:
				count += 1
				value = str(row[0])
				#print value		# Uncomment to print values, but encrypted values may contain non-ascii characters
				binary = ' '.join(format(ord(x), 'b') for x in value)
				tree.add(binary, value)
			print '==========================\n'
			print 'Rows read:\t' + str(count) # 读了多少行
			print 'Unique strings:\t' + str(UNIQUE) # 不同字符串个数
			print '=========================='
			resp = raw_input ('\nWrite unique values to file? [Y/n] ').lower() # 把读入转换成小写
			if resp == 'y':
				print '==========================\n'
				tree.printTREE(count, database+'-'+table+'-'+attribute);
				print 'File "'+database+'-'+table+'-'+attribute+'" created'

			print '=========================='
			resp = raw_input ('\nContinue? [Y/n] ').lower()
			if resp == 'y':
				UNIQUE = 0
			else:
				print 'Good-Bye'
				sys.exit()

if __name__ == "__main__":
	main()
