<html>
<body>
<script>
    function F() {
        var Foo = document.getElementById('id');
        Foo.value = '<%= request.getAttribute("richestUser")%>';
    };
    function F2() {
        var Foo2 = document.getElementById('id2');
        Foo2.value = '<%= request.getAttribute("sum")%>';
    };
</script>
<form action="" >
    <input type="text" id = "id" name="richest user" value = "Richest user " >
    <input type="button" value="get" onclick="F()"/>
    <br/>
    <input type="text" id = "id2" name="accounts sum" value = "Accounts sum " >
    <input type="button" value="get" onclick="F2()"/>
</form>
</body>
</html>