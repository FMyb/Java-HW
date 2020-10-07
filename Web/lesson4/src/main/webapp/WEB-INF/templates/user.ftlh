<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#assign u = ""/>
    <#if handle??>
        <#assign u = c.findBy(users, "handle", handle)!/>
    </#if>
    <#if user_id??>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>
    <#if u?has_content>
        <div class="user-info">
            <img src="/img/no-title.jpg" alt="user image">
            <div class="user-name"> <@c.userlink u/> </div>
            <ul>
                <li>id: ${u.id}</li>
                <li>handle: ${u.handle}</li>
                <li>name: ${u.name}</li>
            </ul>
        </div>
        <div class="next-previous-button">
            <#assign ind = c.findInd(users, u)!>
            <#assign prev = c.findByInd(users, ind - 1)!>
            <#if prev?has_content>
                <a href="/user?handle=${prev.handle}" style="text-decoration: none; font-size: 2rem; color: #3B5998">←</a>
            <#else>
                <span style="margin: 0; color: gray; font-size: 2rem">←</span>
            </#if>
            <#assign next = c.findByInd(users, ind + 1)!>
            <#if next?has_content>
                <a href="/user?handle=${next.handle}" style="text-decoration: none; font-size: 2rem; color: #3B5998">→</a>
            <#else>
                <span style="margin: 0; color: gray; font-size: 2rem">→</span>
            </#if>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
