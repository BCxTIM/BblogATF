# BBlog ATF
## _Automation Testing framework_



[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Designed by Timofei Moiseev 04/09/2021

Supported browsers you could check from DriverManagerType.class



Tests had been tested by Chrome browser. For the firefox had some problems with page, but I don't have time to investigate it now.
You could check report without to run all tests in resources/report

## Known Issues

- For Angular pages sometimes need to wait. It was a bad expirience to set WebDriver wait, but I haven't time yet to fix it.
> Note: Solution: To write Custome Page object Decorator or try to use Selenide
- Test creation user was out of scope, that's why I've created two users and hardcoded data for testing CRUD Articles



## Installation

Clone project.
Be sure if you have installed maven.


```sh
mvn clean install
```



## Testing


Run from CucumberRunner with VM options:

```sh
-ea -Dbrowser=<browser> -Dusername=<username> -Dpassword=<password>
```
> Note:Replace username and password with valid credentials for Basic Auth
> Note:Replace browser for chrome,firefox et.c.
> > Note: Be sure that user haven't any created from him articles before running the tests


If you want to run specific test case - add tag @Run (for example in Feature.file) before scenario or Before Feature
```sh
@RemoveArticle @Run
Feature: Delete article
```

```sh
@Run
  Scenario: Delete created article from container block
```
and in CucumberRunner.class set this tag
```sh
@CucumberOptions(tags = {"@Run"}, ...
```
> Note: You could set any tag what you want. Instead of specific tags mentions in hooks by
```sh
   @After(value = "@RemoveArticle", order = 1)
```




If you want to read logs

```sh
   target/logs/app.log
```
For checking HTML report - 
```sh
   target/cucumber-reports/index.html
```


## Important Things

- Every created PageObject page should extend AbstractPage and override abstract methods
- Every created Page should be added to PageEnum as ClassName Enum
- Use AssertATF.assertThat assertions, or if you need others - override existing and do not forget to set logging
- We are using Cucumber BDD tool, and you can use DataTable class. For converting DataTable to custom object - do not forget to register it in TypeRegistryConfiguration. Custom type save to 'model' package
- For testing purpose Exceptions - use ATFException

## License


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[dill]: <https://github.com/joemccann/dillinger>
[git-repo-url]: <https://github.com/joemccann/dillinger.git>
[john gruber]: <http://daringfireball.net>
[df1]: <http://daringfireball.net/projects/markdown/>
[markdown-it]: <https://github.com/markdown-it/markdown-it>
[Ace Editor]: <http://ace.ajax.org>
[node.js]: <http://nodejs.org>
[Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
[jQuery]: <http://jquery.com>
[@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
[express]: <http://expressjs.com>
[AngularJS]: <http://angularjs.org>
[Gulp]: <http://gulpjs.com>

[PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
[PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
[PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
[PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
[PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
[PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
