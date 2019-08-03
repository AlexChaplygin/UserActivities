function call() {
    var datetimepicker_start = $('#datetimepicker_start').val();
    var datetimepicker_end = $('#datetimepicker_end').val();
    var step = $('#step').val();
    var user = usersSelectObject.getSelectedItemsId();

    var arr = {start: datetimepicker_start, end: datetimepicker_end, userIds: user, step: step};
    $.ajax({
        type: 'POST',
        url: '/activities',
        contentType: 'application/json',
        data: JSON.stringify(arr),
        dataType: 'json',
        success: function (data) {
            $('#results').html(data);

            var map = new Map(Object.entries(data));
            var table_body = '<table border="1">';
            table_body += '<thead>';
            table_body += '<tr>';
            table_body += '<td>';
            table_body += 'Период';
            table_body += '</td>';
            var userValues = usersSelectObject.getSelectedItemsTitle();
            for (var i = 0; i < userValues.length; i++) {
                table_body += '<td>';
                table_body += userValues[i];
                table_body += '</td>';
            }
            table_body += '</tr>';
            table_body += '</thead>';

            for (var [key, value] of map) {
                table_body += '<tr>';

                table_body += '<td>';
                var formattedDate = key.substring(0, key.indexOf('.')).replace('T', ' ');
                table_body += formattedDate;
                table_body += '</td>';

                for (var i = 0; i < userValues.length; i++) {
                    var val = 0;
                    for (var j = 0; j < value.length; j++) {
                        if (userValues[i] === value[j].userName) {
                            val = value[j].activityCount;
                            break;
                        }
                    }
                    table_body += '<td>';
                    table_body += val;
                    table_body += '</td>';
                }
                table_body += '</tr>';
            }

            table_body += '</table>';
            $('#tableDiv').html(table_body);
        },
        error: function (xhr, str) {
            alert('Возникла ошибка: ' + xhr.responseCode);
        }
    });
}

$(document).ready(function () {
    $('#datetimepicker_start').datetimepicker();
    $('#datetimepicker_end').datetimepicker();
});
var usersSelectObject;
$(document).ready(function () {
    $.ajax({
        url: '/activities/users',
        type: 'get',
        success: function (data) {
            var myData = [];
            $.each(data, function (key, modelName) {
                // var jsonData = {};
                var jsonData = {
                    "id": modelName.userId,
                    "title": modelName.userName
                };
                myData.push(jsonData);
            });
            usersSelectObject = $('#users').comboTree({
                source: myData,
                isMultiple: true
            });
        }
    });
});