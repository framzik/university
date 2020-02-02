function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: "ajax/profile/courses/filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get("ajax/profile/courses/", updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: "ajax/profile/courses/",
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": "ajax/profile/courses/",
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
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
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        }),
        updateTable: updateFilteredTable
    });
});