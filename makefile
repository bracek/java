clean.install:
	cd jersey/ && make && cd .. 
	cd hibernate/ && make && cd .. 
	cd jsp/ && make && cd .. 
	cd maven/ && make && cd .. 
	cd phd/ && make && cd .. 
	cd spring/ && make && cd .. 
	cd spring-mvc/ && make && cd .. 
	cd springHibernate/ && make && cd .. 
	cd spring_roo/ && make && cd .. 
	cd velocity/ && make && cd .. 
	cd zk/ && make && cd .. 

run.sonar:
	cd jersey/ && make && cd .. 
	cd hibernate/ && make && cd .. 
	cd jsp/ && make && cd .. 
	cd maven/ && make && cd .. 
	cd phd/ && make && cd .. 
	cd spring/ && make && cd .. 
	cd spring-mvc/ && make && cd .. 
	cd springHibernate/ && make && cd .. 
	cd spring_roo/ && make && cd .. 
	cd velocity/ && make && cd .. 
	cd zk/ && make && cd .. 

clean:
	cd hibernate/ && make clean && cd .. 
	cd jsp/ && make clean && cd .. 
	cd maven/ && make clean && cd .. 
	cd phd/ && make clean && cd .. 
	cd spring/ && make clean && cd .. 
	cd spring-mvc/ && make clean && cd .. 
	cd springHibernate/ && make clean && cd .. 
	cd spring_roo/ && make clean && cd .. 
	cd velocity/ && make clean && cd .. 
	cd zk/ && make clean && cd .. 




