function allowEdit() {
	$("form :submit").show();
	$("form input").attr("readonly", false);
	$("form input").attr("disabled", false);
	$(".datepicker").datepicker();
	$(".allow-edit").hide();
	$("form .identity").attr("readonly", true);
}

$(".allow-edit").click(allowEdit);

if ($("form div .form-group").hasClass("has-error")) {
	allowEdit();
}
