<html>
<div style="background-color:#AABBCD">
    <table style="border:0px">
        <tr style="border:0px" align="center">
            <td colspan="4">以下是今天的主要新闻</td>
        </tr>
        <tr align="center">
            <td>编号</td>
            <td>标题</td>
            <td>发布时间</td>
            <td>操作</td>
        </tr>
        <#if list?exists >
            <#list list as item>
                <tr align="center">
                    <td>${item.userId}</td>
                </tr>
            </#list></#if>
    </table>
</div>
</html>