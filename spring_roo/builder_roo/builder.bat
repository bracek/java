project --topLevelPackage fi.ixonos.builder
persistence setup --provider HIBERNATE --database MYSQL
database properties set --key database.username --value root
database properties set --key database.password --value mysql
database properties set --key database.url --value jdbc:mysql://localhost:3306/builderroo
database properties set --key database.driverClassName --value com.mysql.jdbc.Driver

persistence setup --provider HIBERNATE --database POSTGRES
database properties set --key database.username --value postgres
database properties set --key database.password --value postgres
database properties set --key database.url --value jdbc:postgres//localhost:5432/builderroo
database properties set --key database.driverClassName --value org.postgresql.Driver

persistence setup --provider HIBERNATE  --database HYPERSONIC_IN_MEMORY 


entity  --class ~.domain.Projects 
field string name --notNull --sizeMin 3 --sizeMax 30
field string --fieldName email --regexp b[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}b --notNull --sizeMin 3 --sizeMax 30


entity  --class ~.domain.Symbian 
field string icon --notNull  --sizeMin 3 --sizeMax 30

field reference --class ~.domain.Symbian  --fieldName project --type ~.domain.Projects --joinColumnName project  --cardinality ONE_TO_ONE
field reference --class ~.domain.Projects --fieldName symbian --type ~.domain.Symbian --cardinality ONE_TO_ONE


entity  --class ~.domain.Meego 
field string maintainer --notNull --sizeMin 1 --sizeMax 30
field string home_page --notNull --sizeMin 1 --sizeMax 100
field string short_description --notNull --sizeMin 1 --sizeMax 60
field string long_description --notNull --sizeMin 1 --sizeMax 250
field string display_name --notNull --sizeMin 1 --sizeMax 30

field reference --class fi.ixonos.builder.domain.Meego  --fieldName project --type ~.domain.Projects  --joinColumnName project --cardinality ONE_TO_ONE 
field reference --class fi.ixonos.builder.domain.Projects  --fieldName meego --type ~.domain.Meego --cardinality ONE_TO_ONE



entity  --class ~.domain.Platform 
field string platform_name --notNull --sizeMin 3

entity  --class ~.domain.Templates
field string name --notNull --sizeMin 3



entity  --class ~.domain.NewsTemplate 
field string category_url --notNull --sizeMin 1
field string feed_category_url --class ~.domain.NewsTemplate --notNull --sizeMin 1
field string about_application --class ~.domain.NewsTemplate --notNull --sizeMin 1
field number facebook_id --class ~.domain.NewsTemplate --type int



field reference --class ~.domain.NewsTemplate --fieldName platform --type ~.domain.Platform --joinColumnName platfrom  --cardinality ONE_TO_ONE
field reference --class ~.domain.Platform --fieldName news_template --type ~.domain.NewsTemplate --cardinality ONE_TO_ONE




field reference --class ~.domain.NewsTemplate --fieldName project --type ~.domain.Projects
field set --class ~.domain.Projects --fieldName news_template --type ~.domain.NewsTemplate --mappedBy project --notNull false --cardinality ONE_TO_MANY


controller all --package ~.web


selenium test --controller ~.domain.ProjectsController
selenium test --controller ~.domain.SymbianController
selenium test --controller ~.domain.PlatformCotroller
selenium test --controller ~.domain.NewsTemplateController
selenium test --controller ~.domain.MeegoController




exit 
mvn clean jetty:run




