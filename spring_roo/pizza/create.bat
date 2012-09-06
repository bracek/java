project --topLevelPackage com.springsource.roo.pizzashop 
jpa setup --provider HIBERNATE --database HYPERSONIC_IN_MEMORY
entity jpa --class ~.domain.Topping --testAutomatically 
field string --fieldName name --notNull --sizeMin 2
entity jpa --class ~.domain.Base --testAutomatically 
field string --fieldName name --notNull --sizeMin 2
entity jpa --class ~.domain.Pizza --testAutomatically 
field string --fieldName name --notNull --sizeMin 2
field number --fieldName price --type java.lang.Float
field set --fieldName toppings --type ~.domain.Topping
field reference --fieldName base --type ~.domain.Base
entity jpa --class ~.domain.PizzaOrder --testAutomatically 
field string --fieldName name --notNull --sizeMin 2
field string --fieldName address --sizeMax 30
field number --fieldName total --type java.lang.Float 
field date --fieldName deliveryDate --type java.util.Date
field set --fieldName pizzas --type ~.domain.Pizza
perform tests
perform eclipse
web mvc setup
web mvc all --package ~.web
selenium test --controller ~.web.ToppingController
selenium test --controller ~.web.BaseController
selenium test --controller ~.web.PizzaController
selenium test --controller ~.web.PizzaOrderController
mvn selenium:selenese
backup
perform package


