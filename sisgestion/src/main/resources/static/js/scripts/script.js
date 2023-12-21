const url = "/sisgestion/api/infraccions";

function formatDateDB(fechaOriginal){
	var fecha = new Date(fechaOriginal);

	// Obtener los componentes de la fecha
	var dia = fecha.getUTCDate();
	var mes = fecha.getUTCMonth() + 1; // Los meses en JavaScript son indexados desde 0
	var anio = fecha.getUTCFullYear();
	
	// Formatear la nueva cadena de fecha
	var fechaFormateada = dia + '/' + mes + '/' + anio;
	return fechaFormateada;
}
function formatDate(fechaOriginal){
	var fecha = new Date(fechaOriginal);

	// Obtener los componentes de la fecha
	var anio = fecha.getUTCFullYear();
	var mes = ('0' + (fecha.getUTCMonth() + 1)).slice(-2); // Agrega un cero al mes si es necesario
	var dia = ('0' + fecha.getUTCDate()).slice(-2); // Agrega un cero al día si es necesario
	
	// Formatear la nueva cadena de fecha
	var fechaFormateada = anio + '-' + mes + '-' + dia;
	return fechaFormateada;
}
function save(bandera) {
    $("#modal-update").modal("hide")
    let id = $("#guardar").data("id");    
    let categoria = {
        id: id,
        dni : $("#dni").val(),
        fecha : $("#fecha").val(),
        placa : $("#placa").val(),
        infraccion : $("#infra").val(),
        descripcion : $("#desc").val()
    }
    let metodo = bandera == 1 ? "POST" : "PUT";
    $.ajax({
        type: metodo,
        url: url,
        data: JSON.stringify(categoria),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
			if(data==0){
				Swal.fire({
	                icon: 'error',
	                title: 'La categoría ya esta registrada',
	                showConfirmButton: false,
	                timer: 1500
	            })				
			}else{
	            let texto = bandera == 1 ? "guardado": "actualizado";
	            getTabla();
	            Swal.fire({
	                icon: 'success',
	                title: 'Se ha '+texto+' la categoria',
	                showConfirmButton: false,
	                timer: 1500
	            })
	            clear();
            }
            getTabla();
        },
    }).fail(function () {
        
    });
}

function deleteFila(id) {
    $.ajax({
        type: "DELETE",
        url: url +"/"+ id,
        data: {
            id: id,
        },
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado la categoria',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
    }).fail(function () {

    });

}

/*
function getTabla() {
    let t = $("#tablaCategorias").DataTable();
    t.clear().draw(false);
    let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
        '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

    t.row.add([1, "Categoria 1", botonera]);
    t.row.add([2, "Categoria 2", botonera]);
    t.draw(false);    
}
*/

function getTabla() {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "text",
        cache: false,
        success: function (data) {

            let t = $("#tablaDT").DataTable();
            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

            let js = JSON.parse(data);

            $.each(js, function (a, b) {
                t.row.add([b.id, b.dni,b.fecha,b.placa, b.infraccion, b.descripcion, botonera]);

            });

            t.draw(false);

        },
    }).fail(function () {

    });
}


function getFila(id) {
	$("#modal-update").modal("hide")
    id = $("#guardar").data("id");    
    let inf = {
        id: id,
        dni : $("#dni").val(),
        fecha : $("#fecha").val(),
        placa : $("#placa").val(),
        infraccion : $("#infra").val(),
        descripcion : $("#desc").val()
    }
    $.ajax({
        type: "PUT", 
        url: url + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Categoria");
            $("#nombre").val(data.nombre);
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
    }).fail(function () {
        // Manejar el fallo
    });
}


function clear() {
    $("#modal-title").text("Nueva Categoria");
    $("#nombre").val("");
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
    $("#dni").val("")
    $("#fecha").val("")
    $("#placa").val("")
    $("#infra").val("")
    $("#desc").val("")
}

$(document).ready(function () {

    $("#tablaDT").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ DE _TOTAL_",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "10%" },
            { targets: 1, width: "17%" },
            { targets: 2, width: "13%" },
            { targets: 3, width: "13%" },
            { targets: 4, width: "17%" },
            { targets: 5, orderable: false, width: "20%" },
            { targets: 5, orderable: false, width: "10%" }
        ],
    });

    clear();

    $("#nuevo").click(function () {
        clear();
    });

    $("#guardar").click(function () {
		
        let bandera = $("#guardar").data("bandera");
        save(bandera);
    })

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Categoria',
            text: "¿Esta seguro de querer eliminar esta categoria?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFila(id);
            }
        })
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        let d = $($(this).parents('tr')[0].children[1]).text();
        let f = $($(this).parents('tr')[0].children[2]).text();
        let i = $($(this).parents('tr')[0].children[3]).text();
        let p = $($(this).parents('tr')[0].children[4]).text();
        let de = $($(this).parents('tr')[0].children[5]).text();
        $("#dni").val(d)
	    $("#fecha").val(formatDate(f))
	    $("#placa").val(i)
	    $("#infra").val(p)
	    $("#desc").val(de)
	    $("#modal-title").text("Editar Categoria");
        $("#guardar").data("id", id);
        $("#guardar").data("bandera", 0);
        $("#modal-update").modal("show");
        //getFila(id);
    });
    getTabla();
});