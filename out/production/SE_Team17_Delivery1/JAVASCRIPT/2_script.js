function Popup(idSpecifications) {
    var new_text = prompt("Edit notes:");
    if (new_text != null)
        save_text(idSpecifications, new_text);
}

function save_text(idSpecifications, text_mod) {
    /* Call the microservice and evaluate data and result status */
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/crud_specifications.jsp",
        {
            id: idSpecifications,
            work_note: text_mod
        }, function (data) {
        }).done(function () {
        location.reload();
        alert("Database updated");
    }).fail(function () {
        alert("Error while updating database");
    });
}