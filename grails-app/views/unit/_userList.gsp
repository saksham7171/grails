<g:each in="${user}" var="u">
    ${u}
    <tl:showAdmin isAdmin="${u.admin}">Admin content</tl:showAdmin>
</g:each>