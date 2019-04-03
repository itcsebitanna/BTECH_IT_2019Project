import pandas

t1="PROGRAM"
t2="FULL_MUTATION_SCORE"
t3="      "
t4="PPC"
t5="EPC"


data=pandas.read_csv("m.txt")

name=list(data["PROGRAM"])
epc=list(data["EPC"])
mut=list(data["MUT"])
mm=list(data["MINIMUT"])

print ("%-27s %s" %(t1,t2))

print ("%-25s %-15s %s" %(t3,t4,t5))
for i,j,z,k in zip(name,epc,mut,mm):

	mscore=(z-k)/z
	ms=j/z

	print ("%-25s %-15f %f" %(i,mscore,ms)) 
