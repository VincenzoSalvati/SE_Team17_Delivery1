function next(d) {
    //mi prendo il campo id della riga che seleziono
    $(".cell").click(function () {
        var $row = $(this).closest("tr");    // Trovo la riga 'tr' più vicina
        var $maintainer = $row.find(".id").text(); // Ne prendo il contenuto
        var $perc;
        if (d == mon)
            $perc = $row.find(".link_mon").text().replace("%", "");
        else if (d == tue)
            $perc = $row.find(".link_tue").text().replace("%", "");
        else if (d == wed)
            $perc = $row.find(".link_wed").text().replace("%", "");
        else if (d == thu)
            $perc = $row.find(".link_thu").text().replace("%", "");
        else if (d == fri)
            $perc = $row.find(".link_fri").text().replace("%", "");
        else if (d == sat)
            $perc = $row.find(".link_sat").text().replace("%", "");
        else if (d == sun)
            $perc = $row.find(".link_sun").text().replace("%", "");

        location.href = "4_assignHours.html?activity=" + activity + "&week=" + week + "&time=" + estimatedTime + "&specifications=" + specifications + "&maintainer=" + $maintainer + "&day=" + d + "&strActivity=" + stringActivity + "&strWorkNote=" + stringWorkNote + "&perc=" + $perc;
    });
}