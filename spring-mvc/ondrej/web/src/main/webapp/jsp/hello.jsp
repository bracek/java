<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="/include/header.jsp"%>

<script language="JavaScript">

$( function() {
$('#nokia').click( function() {
window.location = "inventory.do";
return false;
});
});

<%/* character counter test
$( function() {
$("textarea[@id^='text']").keyup( function() {
var text =  $(this).val(); 
var id = $(this).attr('id');
$('#count'+id.substring(4)).val(500-text.length);
}).keyup();
});
*/%>
</script>

<div class="alignCenter">
<h1>
<fmt:message key="hello.heading" />
</h1>
<p>
<fmt:message key="hello.text" />
<c:out value="${now}" />
</p>
<div style="padding-top: 30px;">
<img id="nokia" alt="<fmt:message key='nokia_n95'/>"
title="<fmt:message key='nokia_n95'/>" src="img/nokia-n95.png"
class="link" />
</div>

<%/* character counter test
<div>
<textarea id="text1" cols="10" rows="7"></textarea>
<input id="count1" type="text"></input>
<br />
<textarea id="text2" cols="10" rows="7"></textarea>
<input id="count2" type="text"></input>
</div>
*/%>

<%@ include file="/include/footer.jsp"%>