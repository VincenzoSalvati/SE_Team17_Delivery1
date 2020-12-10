//serve a far uscire il check quando premo il minuto e a calcolare la somma totale
function selectHour(checkId, d) {
    var check = document.getElementById(checkId);
    var val = parseInt(document.querySelector('.' + d).textContent.replace(" min", ""));

    //se il valore è 0 non posso prenderlo per assegnare del tempo, dunque non mostro
    //il check
    if (val === 0) {
        check.style.visibility = 'hidden';
        $('#alertWarning').show('fade');
    } else {
        if (check.style.visibility == 'visible') {
            check.style.visibility = 'hidden';

            //rimuovere il valore dal dizionario
            delete dict[d];

            current_time += parseInt(val);
            if (current_time > 0) {
                document.getElementById("totalMin").innerHTML = current_time;
            } else if (current_time <= 0) {
                document.getElementById("totalMin").innerHTML = "Time expired";
                disableAllButton();
                //rendo disponibile il button Forward
                document.querySelector('.btn-primary').style.pointerEvents = 'auto';
                document.querySelector('.btn-primary').style.opacity = 1;
            }
        } else {
            check.style.visibility = 'visible';

            //aggiungo il valore al dizionario di valori selezionati
            dict[id] = d;
            id++;
            current_time -= parseInt(val);
            console.log('CURRENT TIME PRE', current_time)
            if (current_time > 0) {
                console.log('CURRENT TIME if > 0', current_time)
                document.getElementById("totalMin").innerHTML = current_time;
            } else if (current_time <= 0) {
                console.log('CURRENT TIME if < 0', current_time)
                document.getElementById("totalMin").innerHTML = "Time expired";
                disableAllButton();
                //rendo disponibile il button Forward
                document.querySelector('.btn-primary').style.pointerEvents = 'auto';
                document.querySelector('.btn-primary').style.opacity = 1;
            }
        }
    }

}

//Prepare params to be updated in the DB
function initParams() {

    //mi prendo tutti i valori dalla riga HTML
    avail_8to9 = parseInt(document.querySelector('.link_8to9').textContent.replace(" min", ""));
    avail_9to10 = parseInt(document.querySelector('.link_9to10').textContent.replace(" min", ""));
    avail_10to11 = parseInt(document.querySelector('.link_10to11').textContent.replace(" min", ""));
    avail_11to12 = parseInt(document.querySelector('.link_11to12').textContent.replace(" min", ""));
    avail_14to15 = parseInt(document.querySelector('.link_14to15').textContent.replace(" min", ""));
    avail_15to16 = parseInt(document.querySelector('.link_15to16').textContent.replace(" min", ""));
    avail_16to17 = parseInt(document.querySelector('.link_16to17').textContent.replace(" min", ""));

    id_availability = document.getElementById('link_id').innerHTML;
    console.log('ID availability', id_availability);

    //mi prendo l'ultimo elemento del dict
    lastElem = dict[id - 1];
    console.log('LAST ELEM ID', lastElem);

    //noi in lastElem abbiamo l'ID dell'ultimo elemento selezionato
    lastElemValue = parseInt(document.querySelector('.' + lastElem).textContent.replace(" min", ""));
    console.log('LAST ELEM VALUE', lastElemValue);
    console.log('CURRENT TIME', current_time);

    lastElemValue = Math.abs(current_time);
    console.log('LAST ELEM VALUE UPDATE', lastElemValue);

    console.log('ID DICT', id);

    //itero il mio dizionario
    for (var key in dict) {
        console.log('key i-esima', key)
        switch (dict[key]) {
            case "link_8to9":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_8to9 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_8to9 = lastElemValue;
                }
                break;
            case "link_9to10":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_9to10 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_9to10 = lastElemValue;
                }
                break;
            case "link_10to11":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_10to11 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_10to11 = lastElemValue;
                }
                break;
            case "link_11to12":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_11to12 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_11to12 = lastElemValue;
                }
                break;
            case "link_14to15":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_14to15 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_14to15 = lastElemValue;
                }
                break;
            case "link_15to16":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_15to16 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_15to16 = lastElemValue;
                }
                break;
            case "link_16to17":
                if (key < id - 1) {
                    console.log('not last elem')
                    avail_16to17 = 0;
                } else if (key == id - 1) {
                    console.log('last elem')
                    avail_16to17 = lastElemValue;
                }
                break;
            default:
                break;
        }
    }

    console.log('INIT PARAMS');
    console.log('avail_8to9', avail_8to9);
    console.log('avail_9to10', avail_9to10);
    console.log('avail_10to11', avail_10to11);
    console.log('avail_11to12', avail_11to12);
    console.log('avail_14to15', avail_14to15);
    console.log('avail_15to16', avail_15to16);
    console.log('avail_16to17', avail_16to17);

}

//UPDATE DB
function updateDB() {

    console.log('UPDATE DB');
    console.log('avail_8to9', avail_8to9);
    console.log('avail_9to10', avail_9to10);
    console.log('avail_10to11', avail_10to11);
    console.log('avail_11to12', avail_11to12);
    console.log('avail_14to15', avail_14to15);
    console.log('avail_15to16', avail_15to16);
    console.log('avail_16to17', avail_16to17)

    console.log("http://" + JAVA_TOMCAT_HOST + "/Esame/crud_availability.jsp",
        {
            id: id_availability,
            avail_8to9: avail_8to9,
            avail_9to10: avail_9to10,
            avail_10to11: avail_10to11,
            avail_11to12: avail_11to12,
            avail_14to15: avail_14to15,
            avail_15to16: avail_15to16,
            avail_16to17: avail_16to17
        });

    /* Call the microservice and evaluate data and result status */
    $.post("http://" + JAVA_TOMCAT_HOST + "/Esame/crud_availability.jsp",
        {
            id: id_availability,
            avail_8to9: avail_8to9,
            avail_9to10: avail_9to10,
            avail_10to11: avail_10to11,
            avail_11to12: avail_11to12,
            avail_14to15: avail_14to15,
            avail_15to16: avail_15to16,
            avail_16to17: avail_16to17
        }, function (data) {
        }).done(function () {
    }).fail(function () {
        alert("Error while updating database");
    });
}

//CHECK ERROR DB UPDATE
function checkIfError(data) {
    $.each(data, function (index, obj) {
        console.log(obj.result_code);
    });

}

//serve a disabilitare la scelta dei minuti (Quando finisco il tempo estimated)
function disableAllButton(flag) {
    document.getElementById("link_8to9").style.cursor = 'not-allowed';
    document.getElementById("link_8to9").style.opacity = 0.5;
    document.getElementById("link_8to9").style.pointerEvents = 'none';

    document.getElementById("link_9to10").style.cursor = 'not-allowed';
    document.getElementById("link_9to10").style.opacity = 0.5;
    document.getElementById("link_9to10").style.pointerEvents = 'none';

    document.getElementById("link_10to11").style.cursor = 'not-allowed';
    document.getElementById("link_10to11").style.opacity = 0.5;
    document.getElementById("link_10to11").style.pointerEvents = 'none';

    document.getElementById("link_11to12").style.cursor = 'not-allowed';
    document.getElementById("link_11to12").style.opacity = 0.5;
    document.getElementById("link_11to12").style.pointerEvents = 'none';

    document.getElementById("link_14to15").style.cursor = 'not-allowed';
    document.getElementById("link_14to15").style.opacity = 0.5;
    document.getElementById("link_14to15").style.pointerEvents = 'none';

    document.getElementById("link_15to16").style.cursor = 'not-allowed';
    document.getElementById("link_15to16").style.opacity = 0.5;
    document.getElementById("link_15to16").style.pointerEvents = 'none';

    document.getElementById("link_16to17").style.cursor = 'not-allowed';
    document.getElementById("link_16to17").style.opacity = 0.5;
    document.getElementById("link_16to17").style.pointerEvents = 'none';

}

//serve a riabilitare la scelta dei minuti (Quando faccio CLEAR)
function enableAllButton() {
    document.getElementById("link_8to9").style.cursor = 'default';
    document.getElementById("link_8to9").style.opacity = 1;
    document.getElementById("link_8to9").style.pointerEvents = 'auto';

    document.getElementById("link_9to10").style.cursor = 'default';
    document.getElementById("link_9to10").style.opacity = 1;
    document.getElementById("link_9to10").style.pointerEvents = 'auto';

    document.getElementById("link_10to11").style.cursor = 'default';
    document.getElementById("link_10to11").style.opacity = 1;
    document.getElementById("link_10to11").style.pointerEvents = 'auto';

    document.getElementById("link_11to12").style.cursor = 'default';
    document.getElementById("link_11to12").style.opacity = 1;
    document.getElementById("link_11to12").style.pointerEvents = 'auto';

    document.getElementById("link_14to15").style.cursor = 'default';
    document.getElementById("link_14to15").style.opacity = 1;
    document.getElementById("link_14to15").style.pointerEvents = 'auto';

    document.getElementById("link_15to16").style.cursor = 'default';
    document.getElementById("link_15to16").style.opacity = 1;
    document.getElementById("link_15to16").style.pointerEvents = 'auto';

    document.getElementById("link_16to17").style.cursor = 'default';
    document.getElementById("link_16to17").style.opacity = 1;
    document.getElementById("link_16to17").style.pointerEvents = 'auto';
}

//serve a pulire tutte le scelte fatte (CLEAR)
function clearHour() {
    //ovviamente quando faccio la clear e quindi riabilito i button, mi resetto la variabile current_time
    current_time = estimatedTime;

    for (var i = 0; i < 7; i++) {
        document.getElementById("checkId" + i).style.visibility = 'hidden';
        document.getElementById("totalMin").innerHTML = estimatedTime;
    }
    enableAllButton();

    //disattivo il button Forward
    document.querySelector('.btn-primary').style.pointerEvents = 'none';
    document.querySelector('.btn-primary').style.opacity = 0.5;

    console.log('CURRENT TIME CLEAR', current_time)
}
