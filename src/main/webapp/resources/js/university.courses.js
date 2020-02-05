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
        "createdRow": function (row, data, dataIndex) {
            $(row).attr("data-cost", data.more);
        },
    },
        updateTable: updateFilteredTable
    });
});