# tenant-demo
This small application uses GORM's [multi-tenancy](http://gorm.grails.org/latest/hibernate/manual/#multiTenancy) to host a same application for multiple accounts (SaaS).

The application is initialized with two tenants:
* "Local Host" sells animal products. Connect to [http://localhost:8080/](http://localhost:8080/) as `me`/`password`
* "Home Sweet Home" sells restaurant products. Connect to [http://127.0.0.1:8080/](http://127.0.0.1:8080/) as `me`/`secret`
