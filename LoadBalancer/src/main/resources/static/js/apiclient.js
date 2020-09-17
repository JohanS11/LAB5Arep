var apiclient = (function () {

    var url = "http://localhost:9001/GetMessages";
    var conta = 0;
    function addMessage (){
        var mensaje=document.getElementById("data").value;
        axios.post(url,mensaje)
            .then(res => {
                    getMessages();
                }
            )
    }
    function getMessages(){

        $("#messages > tbody").empty();
        axios.get(url).then(res=>{
            console.log(res.data)
            res.data.mensajes.map(message=>{
                $("#messages > tbody").append(
                    "<tr>" +
                    "<th scope='row'>"+ (conta+1) + "</th>"+
                    " <td>" + message.mensaje + "</td>" +
                    "<td>" + message.fecha + "</td> " +
                    "</tr>"
                );
            })
        })
    }

    return {
        addMessage : addMessage,
        getMessages:getMessages
    };
})();