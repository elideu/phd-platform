/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    getStudentsList();

});

//servlet per avere la lista dei phd student, per riempire la select
function getStudentsList()
{
    $.getJSON("GetPhdStudentList", function (data) {
        // $("#phdStudetsList option").remove();
        $.each(data.account, function (index, value) {
            phdstudent = "<option class='optionItem' value='" + value.secondaryEmail + "'> " + value.name + " " + value.surname + " </option> ";
            $("#phdStudentsList").append(phdstudent);
        });
    });
}

//funzione chiamata dall'onclick della select
function selectedItem()
{
    $("#TutorNameField").html("nessun tutor");
    $("#removeTutorButton").hide();
    $("#professorListTable tr").remove();
    selectedAccount = $("#phdStudentsList option:selected").val(); // la chiave primaria di account

    if (selectedAccount !== "default") //se il valore della select è default non mostriamo il div contenente le informazioni
    {
        $("#panelDiv").show();

        //servlet per richiamare il tutor
        $.getJSON("GetTutorServlet", {fkAccount: selectedAccount}, function (data) {
            $("#removeTutorButton").show();
            $("#TutorNameField").html(" <b> " + data.name + " " + data.surname + " </b> ");
            tutorKey = data.fkAccount; //salviamo la mail del professore 
        });

        //servlet per riempire la tabella con tutti i professori
        $.getJSON("GetProfessorsList", function (data) {
            $.each(data.account, function (index, value) {
                professor = "<tr> <td> " + value.name + "</td> <td> " + value.surname + "</td>   <td> <button class='btn btn-orange' id=" + value.secondaryEmail + " onclick='addTutorButton(" + 'id' + ")' > <span class='glyphicon glyphicon-sort' aria-hidden='true' ></span> Aggiorna </button>  </td>  </tr> "; // la secondaryEmail è la chiave primaria del professore che dovrà essere settato come nuovo tutor
                $("#professorListTable").append(professor);
            });
        });
    }
    else
        $("#panelDiv").hide();
}

//funzione per aggiornare il tutor, chiamata dall'onclick dei bottoni generali nella tabella contenente la lista dei professori
function addTutorButton(newProfessorkey)
{
    //in newProfessorkey abbiamo la mail del nuovo tutor
    this.newProfessorkey = newProfessorkey;

    var studentMail = $("#phdStudentsList option:selected").val();
    //in studentMail abbiamo la mail dello studente selezionato nella select a cui bisogna assegnare il tutor 
    //in tutorKey abbiamo la mail del professore gia selezionato come tutor , se c'è (serve nel caso dobbiamo rimuoverlo)

    tutorName = $("#TutorNameField").html();
    if (tutorName === 'nessun tutor') { //non c'è un tutor assegnato, dobbiamo soltanto aggiungercelo

        //servlet per assegnare il tutor  
        $.getJSON("InsertStudentTutor", {idStudent: studentMail, idProfessor: newProfessorkey}, function (data) {
            $("#ModificaDialog").modal();
            selectedItem();
        });

    }
    else { //c'è gia un tutor assegnato: dobbiamo  aggiornarlo
        if (tutorKey === newProfessorkey)
        {
            $("#ErroreDialog").modal();
        }
        else {
            //servlet per fare l'update del tutor
            $.getJSON("UpdateTutorServlet", {idProfessor: newProfessorkey, idStudent: studentMail}, function (data) {
                $("#ModificaDialog").modal();
                selectedItem();
            });
        }
    }

}

function removeTutorButton()
{
    var studentMail = $("#phdStudentsList option:selected").val();
    //servlet per rimuovere il tutor assegnato 
    $.getJSON("DeleteTutorServlet", {idStudent: studentMail}, function (data) {
        $("#CancellazioneDialog").modal();
        $("#removeTutorButton").hide();
        $("#TutorNameField").html("nessun tutor");
    });
}
