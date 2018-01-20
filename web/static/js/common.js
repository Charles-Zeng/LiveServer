
function doPost (url,args)
{
    var myForm = document.createElement("form");
    myForm.method = "post";
    myForm.action = url;
    for ( var k in args) {
        var myInput = document.createElement("input");
        myInput.setAttribute("name", k);
        myInput.setAttribute("value", args[k]);
        myForm.appendChild(myInput);
    }
    document.body.appendChild(myForm);
    myForm.submit();
    document.body.removeChild(myForm);
}
