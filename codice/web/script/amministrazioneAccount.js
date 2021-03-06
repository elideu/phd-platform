/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getAccountList();
    searchForName();
    $("#changeDiv").hide();
});



function getAccountList() {

    $.getJSON("getAccountList", function (data) {

        $.each(data.account, function (index, value) {
            var type = value.typeAccount;
            var realType;

            switch (type) {
                case "phdstudent":
                    realType = "Dottorando";
                    break;
                case "professor":
                    realType = "Docente";
                    break;
                case "basic":
                    realType = "Base";
                    break;
            }
            account = "<tr><td> " + value.name + " " + value.surname
                    + "</td><td> " + value.secondaryEmail + "</td>"
                    + "<td> " + realType + "</td>"
                    + "<td><button class='btn btn-blu' id=" + value.email +
                    " onclick='changeType(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Modifica </button>  </td></tr>";
            $("#accountListTable").append(account);

        });

    });

}


function searchForName()
{

    $('#word').on("keyup", function () {
        $("#bodyTable tr").remove();
        var name = $("#word").val();
        if (name == "")
            getAccountList();
        else {
            $.getJSON("SearchUser", {name: name}, function (data) {

                $.each(data.account, function (index, value) {
                    var type = value.typeAccount;
                    var realType;

                    switch (type) {
                        case "phdstudent":
                            realType = "Dottorando";
                            break;
                        case "professor":
                            realType = "Docente";
                            break;
                        case "basic":
                            realType = "Base";
                            break;
                    }
                    account = "<tr><td> " + value.name + " " + value.surname
                            + "</td><td> " + value.secondaryEmail + "</td>"
                            + "<td> " + realType + "</td>"
                            + "<td><button class='btn btn-blu' id=" + value.email +
                            " onclick='changeType(" + 'id' + ")' > <span class='glyphicon glyphicon-user' aria-hidden='true' ></span> Modifica </button>  </td></tr>";
                    $("#accountListTable").append(account);

                });
            });

        }

        $("#changeDiv").hide();

    });


}



function changeType(email) {
    $("#adminBox").unbind('click');
    $("#typeSelect").unbind('change');

    var oldtype;
    $("#typeSelect .added").remove();
    $.getJSON("GetAccountbyEmail", {index: email}, function (data) {
        $("#selectedName").html(" <b>" + data.name + " " + data.surname + "</b>");
        if (data.isAdministrator) {
            $("#adminBox").prop("checked", true);
        } else
            $("#adminBox").prop("checked", false);

        $("#adminBox").on("click", function () {
            var secEmail = data.secondaryEmail;
            var bool = $("#adminBox").prop("checked");

            $.getJSON("SetAdmin", {secondaryEmail: secEmail, checked: bool}, function () {
                $("#bodyTable tr").remove();
                getAccountList();
                $("#changeDiv").hide();
            });
            
            $("#modifiedModal").modal();


        });


        oldtype = data.typeAccount;

        switch (oldtype) {
            case "phdstudent":
                $("#typeSelect").append("<option class='added' value='professor'>Docente</option>");
                $("#typeSelect").append("<option class='added' value='basic'>Utente Base</option>");
                break;
            case "basic":
                $("#typeSelect").append("<option class='added' value='professor'>Docente</option>");
                $("#typeSelect").append("<option class='added' value='phdstudent'>Dottorando</option>");
                break;
            case "professor":
                $("#typeSelect").append("<option class='added' value='phdstudent'>Dottorando</option>");
                $("#typeSelect").append("<option class='added' value='basic'>Utente Base</option>");
                break;
        }
    });

    $("#typeSelect").change(function () {
        type = $("#typeSelect option:selected").val();

        $.getJSON("ChangeTypeServlet", {userEmail: email, newType: type}, function (data) {
            $("#bodyTable tr").remove();
            getAccountList();
            $("#changeDiv").hide();
        });
        
        $("#modifiedModal").modal();
    });


    $("#changeDiv").show();
}






