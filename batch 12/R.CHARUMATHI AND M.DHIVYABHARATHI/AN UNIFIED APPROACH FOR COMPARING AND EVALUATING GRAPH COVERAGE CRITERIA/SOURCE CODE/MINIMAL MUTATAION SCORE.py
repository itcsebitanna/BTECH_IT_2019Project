import pandas

t1="PROGRAM"
t2="MINIMAL_MUTATION_SCORE"
t3="      "
t4="PPC"
t5="EPC"
t6="EC"


data=pandas.read_csv("d.txt")

name=list(data["PROGRAM"])
ppc=list(data["PPC"])
epc=list(data["EPC"])
ec=list(data["EC"])
mut=list(data["MUT"])


print ("%-27s %s" %(t1,t2))

print ("%-25s %-5s %3s %5s" %(t3,t4,t5,t6))
for i,j,k,m,z in zip(name,ppc,epc,ec,mut):

	mmsp=j/z
	mmse=k/z
	mmsec=m/z

	print ("%-25s %.3f %.3f %.3f" %(i,mmsp,mmse,mmsec)) 
