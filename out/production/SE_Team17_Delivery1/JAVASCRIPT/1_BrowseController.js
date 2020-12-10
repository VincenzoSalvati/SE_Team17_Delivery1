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
    initBrowseView() {

        let microServiceEndpoints = [
            // 0) JSON Static, we used it for defining the data interface of a generic record for updating
            "jsonprototypes/address-book-record-prototype.json",

            // 1) JSON Static, we used it for defining the data interface of a new record for adding
            "jsonprototypes/address-book-new-record-prototype.json",

            // 2) A PHP implementation of JSON service
            "services/address-book-record-get.php?activity=",

            // 3) A Java JSP implementation of JSON service
            "http://" + JAVA_TOMCAT_HOST + "/Esame/1_showActivities.jsp"
        ];

        let selectedMicroServiceEndpoint = microServiceEndpoints[3];

        let controller = this;

        /* Call the microservice and evaluate data and result status */
        $.getJSON(selectedMicroServiceEndpoint, function (data) {
            controller.renderGUI(data);
        }).done(function () {
            controller.showMessageStatus("green", "All done");
        }).fail(function () {
            controller.showMessageStatus("red", "Error while requesting service: " + controller.serviceEndPoint);
        });

        this.showMessageStatus("black", "Requesting data from service: " + this.serviceEndPoint);
    }

    /**
     * Render the given JSON data into GUI static design
     *
     * @note Uses the static HTML of #address-book-row template as a row template.
     *       Then, for each data row in the given JOSN data array it dynamically
     *       render the single row HTML with data of the current iteration.
     *       After any iteration, the rendered row HTML is appended into
     *       #address-book-rows table body.  On ending iterations #address-book-rows HTML
     *       will be dynamically generated with the given JSON data array.
     *
     * @param  data a JSON representation of data
     */
    renderGUI(data) {

        let controller = this;

        /* Get the html template for table rows */
        let staticHtml = $("#activity-row-template").html();

        /* Bind obj data to the template, then append to table body */
        $.each(data, function (index, obj) {
            let row = staticHtml;
            row = row.replace(/{Id}/ig, obj.id);
            row = row.replace(/{Area}/ig, obj.area);
            row = row.replace(/{Type}/ig, obj.type);
            row = row.replace(/{EstimatedTime}/ig, obj.estim_time);

            row = row.replace()
            $('#activity-rows').append(row);

        });

        /* When empty address-book */
        if (data.length === 0) {
            $("tfoot :first-child").hide();
            $("tfoot").html('<tr><th colspan="6">No records</th></tr>');
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