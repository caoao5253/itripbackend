<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>生成二维码</title>
</head>
<script src="${pageContext.request.contextPath }/statics/js/jquery-1.12.4.js" type="text/javascript"></script>
<script src=${pageContext.request.contextPath }/statics/js/qrcode.js type="text/javascript"></script>
<body>
<div id="qrcode"></div>
<script>
    $.ajax({
        url :"${pageContext.request.contextPath}/api/wx/createCode/D100000120170627141912feb0f4",
        method : "GET",
        success : function(data) {
            new QRCode(document.getElementById('qrcode'), data.data.code_url);
        }
    });
</script>
</body>
</html>

