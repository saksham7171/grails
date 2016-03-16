Hello,${user.name}<br>,
You have following unread items:<br>
<table>
<th>S.No.</th>
<th>From Topic</th>


<g:each var="resource" in="${unreadResources}" status="i">
    <tr>
        <td>${i}</td>
        <td>${resource.topic}</td>
    </tr>
</g:each>

</table>