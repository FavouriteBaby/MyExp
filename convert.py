# coding=UTF-8
import os

class Convert:
	def __init__(self):
		pass

	def init(self, inputFile, tshark, outputFile):
		self.inputFile = inputFile
		self.command = ''
		self.tshark = tshark
		self.outputFile = outputFile

	def convertToCSV(self):
		self.command = self.getCommand()
		print(self.command)
		os.system(self.command)

	def getCommand(self):
		cmd = ''
		if self.hasFile(self.inputFile):
			cmd = self.tshark + ' -r ' + self.inputFile + ' -T fields -e _ws.col.Source -e _ws.col.Destination -e _ws.col.SrcPort -e _ws.col.DstPort -E separator=, -E header=y > ' + self.outputFile
		return cmd

	def hasFile(self, filename):
		if os.path.exists(filename):
			return True
		else:
			return False

	@staticmethod
	def renameFile(prefix):
		pwd = os.getcwd()
		print(pwd)
		for file in os.listdir(pwd):
			if prefix in file:
				name = file.split('_')
				newName = name[0] + '_' + name[1] + '.tcpdump'
				os.rename(file, newName)
				'''
				name = file.split('.')
				newName = name[0]
				os.rename(file, newName)
				'''

if __name__ == '__main__':
	surfix = '.tcpdump'
	tshark = "D:\Progra~1\Wireshark\\tshark"
	inputFile = ''
	outputFile = "G:\MyExp\Dataset\DARPA\DARPA1999\Week1\Monday\inside.tcpdump\Week1_Monday_CSV\\"

	objConvert = Convert()
	pwd = os.getcwd()
	print(pwd)
	index = 0
	
	for file in os.listdir(pwd):
		if surfix in file:
			inputFile = file
		else:
			continue
		
		objConvert.init(inputFile, tshark, outputFile+str(index)+'.csv')
		objConvert.convertToCSV()
		index = index + 1