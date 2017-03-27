Ukraine, Nikolaev.
Kozheurov Yuriy Aleksandrovich
Email: kya@bk.ru
Skype: kyaacdc
Phone: +38 (050) 333-71-78

Run ServerStarter for start REST SpringBoot server. And run ClientStarter for start Apache Http CLI REST client (JSON format)
Or run JUnit Mockito tests.

1) For list all deposits input in command line - LIST (case ignored)    or input in internet browser this reference - http://localhost:8080/account
2) For see summ of all deposits in bank input - SUM    or (http://localhost:8080/account/search/getSumAllAccounts)
3) For see count deposits in bank - COUNT    or (http://localhost:8080/account/search/countBy)
4) For show deposit info by account - INFO ACCOUNT <account_id>     or http://localhost:8080/depositor/account_id
5) For show list deposits by customer name - INFO DEPOSITOR <depositor>    or http://localhost:8080/account/search/findByDepositor?depositor={depositor}
6) For select deposits by type - SHOW TYPE <type> (type is one of - PosteRestante, Urgent, Rated, Cumulative, Savings, Metallic)    or (http://localhost:8080/account/search/findByTypeDeposit?typeDeposit={type})
7) For select deposits by bank - SHOW BANK <name>    or (http://localhost:8080/account/search/findByBankNameIgnoreCase?bankName={name})
8) For add new deposit () info -  ADD <deposit info ... > (ADD <id> <amount> <bank_name> <country> <profitabitity> <timeConstraints> <typeDeposit> <DepositorsEmail>");)
9) For add new customer -  ADDCUSTOMER  <email> <name>
10) For remove deposit - DELETE <account_id>