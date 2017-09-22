import csv
import time
import os

class Operate(object):
	def __init(self):
		pass

	def init(self, readFile, writeFile):
		self.readFile = readFile
		self.writeFile = writeFile
		self.sourceIP = []
		self.destinationIP = []
		self.sourcePort = []
		self.destinationPort = []
		self.duration = []
		print(self.readFile)
		print(self.writeFile)

	def readCSV(self):
		with open(self.readFile, 'rb') as f:
			reader = csv.reader(f)
			for row in reader:
				self.readRow(row)

	def readRow(self, row):
		if(row[2] != ''):
			self.sourceIP.append(row[0])
			self.destinationIP.append(row[1])
			self.sourcePort.append(row[2])
			self.destinationPort.append(row[3])

	'''
	def readRow(self, row):
		self.sourceIP.append(row[2])
		self.destinationIP.append(row[3])
		info = row[6].split(' ')
		length = len(info)
		if '>' in info:
			index = info.index('>')
			self.sourcePort.append(info[index-1])
			self.destinationPort.append(info[index+1])
		else:
			self.sourcePort.append(' ')
			self.destinationPort.append(' ')
	'''
	'''
	def readRow(self, row):
		if row[4] == 'TCP' or row[4] == 'UDP' or row[4] == 'ICMP':
			self.duration.append(row[1])
			self.sourceIP.append(row[2])
			self.destinationIP.append(row[3])
			info = row[6].split(' ')
			length = len(info)
			if '>' in info:
				index = info.index('>')
				self.sourcePort.append(info[index-2])
				self.destinationPort.append(info[index+2])
			else:
				self.sourcePort.append(' ')
				self.destinationPort.append(' ')
	'''

	def writeTXT(self):
		length = len(self.sourceIP)
		with open(self.writeFile, 'w') as f:
			for index in range(1, length):
				self.writeLine(f, index)

	def writeLine(self, file, index):
		content = str(self.sourceIP[index]) + ',' + str(self.destinationIP[index]) + ',' + str(self.sourcePort[index]) + ','  + str(self.destinationPort[index]) + '\n'
		#content = str(self.sourceIP[index]) + ',' + str(self.destinationIP[index]) + '\n'
		file.writelines(content)

if __name__ == '__main__':
	objOP = Operate()
	index = 0
	inputFile = str(index) + '.csv'
	outputFile = str(index) + '.txt'

	while(os.path.exists(inputFile)):
		objOP.init(inputFile, outputFile)
		optime = time.time()
		objOP.readCSV()
		readtime = time.time()
		objOP.writeTXT()
		writetime = time.time()
		print("read time %s", readtime - optime)
		print("write time %s", writetime - readtime)
		
		index = index + 1
		inputFile = str(index) + '.csv'
		outputFile = str(index) + '.txt'

