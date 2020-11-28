<#ftl encoding="UTF-8"/>
<#import "spring.ftl" as spring />
<#macro content></#macro>
<#macro main>
    <!doctype html>
    <html lang="en">

    <body>
    <nav class="navbar navbar-light bg-light">
    </nav>
    <@content/>
    </body>
    </html>
</#macro>