<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>freemaker模板引擎</title>
</head>
<body>
<table border="1">
    <tr>
        <td>编号</td>
        <td>用户名</td>
        <td>地址</td>
        <td>性别</td>
    </tr>

<#-- 遍历值的类型  传过来的键    变量名   -->
    <#list     users      as u>
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.address}</td>
            <td>
                <#if u.gender==0>
                    男
                    <#elseif u.gender==1 >
                    女
                    <#else>
                    未知
                </#if>

            </td>
        </tr>
    </#list>


</table>
</body>
</html>