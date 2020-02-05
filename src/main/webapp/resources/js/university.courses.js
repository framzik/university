var courseAjaxUrl = "ajax/profile/courses/";
function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: courseAjaxUrl+"filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(courseAjaxUrl, updateTableByData);
}


$(function () {
    makeEditable({
        ajaxUrl: courseAjaxUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "number"
                },
                {
                    "data": "cost"
                    // "className": "blue"
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false
                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "rowCallback":
                function (row, data) {
                //debugger;
                if(data["cost"]>16000){
                    $(row).attr("data-courseCost", false);
                } else {
                    $(row).attr("data-courseCost", true);
                }
                // else $('td', row).css('background-color', 'Green');
            },
    },
        updateTable: updateFilteredTable
    });
});