const JAVA_TOMCAT_HOST = "192.168.1.5:8080";

class BrowseController {

    /**
     *  The service endpoint providing JSON data
     */

    constructor() {

    }

    /**
     * Fetch JSON data from the micro service, then call a function for rendering the View
     *
     * @use renderGUI(), showMessageStatus()
     */
    initBrowseView(activity, week) {

        let microServiceEndpoints = [
                // 0) JSON Static, we used it for defining the data interface of a generic record for updating
                "jsonprototypes/address-book-record-prototype.json",

                // 1) JSON Static, we used it for defining the data interface of a new record for adding
                "jsonprototypes/address-book-new-record-prototype.json",

                // 2) A PHP implementation of JSON service
                "services/address-book-record-get.php?activity=" + activity,

                // 3) A Java JSP implementation of JSON service
                "http://" + JAVA_TOMCAT_HOST + "/Esame/2_showSpecifications.jsp?activity=" + activity + "&week=" + week
            ]
        ;

        let selectedMicroServiceEndpoint = microServiceEndpoints[3];

        let controller = this;

        /* Call the microservice and evaluate data and result status */
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            //controller.showMessageStatus("green","All done");
        }).fail(function () {
            //controller.showMessageStatus("red","Error while requesting service: " + controller.serviceEndPoint);
        });

        //this.showMessageStatus("black","Requesting data from service: " + this.serviceEndPoint);
    }

    /**
     * Render the given JSON data into GUI static design
     *
     * @note Uses the static HTML of #specifications-row template as a row template.
     *       Then, for each data row in the given JSON data array it dynamically
     *       render the single row HTML with data of the current iteration.
     *       After any iteration, the rendered row HTML is appended into
     *       #specifications-rows table body.  On ending iterations #specifications-rows HTML
     *       will be dynamically generated with the given JSON data array.
     *
     * @param  data a JSON representation of data
     */
    renderGUI(data) {
        /* Get the html template for table rows */
        let staticHtml = $("#specifications-row-template").html();
        let activityNav;
        let skl;
        let workNote;

        /* Bind obj data to the template, then append to table body */
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            workNote = obj.work_note;
            row = row.replace(/{Work_note}/ig, workNote);
            row = row.replace(/{Int_des}/ig, obj.int_des);
            activityNav = obj.id_activity;
            row = row.replace(/{Id_activity}/ig, activityNav);
            row = row.replace(/{Week_activity}/ig, obj.week_activity);
            skl = obj.skill;
            row = row.replace(/{Skill}/ig, skl);
            $('#specifications-rows').append(row);
        });


        $("#strAct").append("Activity to assign:  " + activityNav);
        $("#strSkl").append(skl);
        $("#strWorkNot").append(workNote);


        /* When empty specifications */
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="5">No records</th></tr>');
        }
    }

    /**
     * Shows the given message with the given color into
     * GUI #request-status HTML element
     *
     * @param color string Message Color
     * @param message string Message text
     */
    showMessageStatus(color, message) {
        $("#request-status").css("color", color);
        $("#request-status").html(message);
    }

}