jQuery(document).ready(function() {
	/* alert(context); */
});

function openModal(selector) {
	var trigger = jQuery(selector);
	var yes = jQuery(trigger.attr('rel')).find('.btn-modal-yes').eq(0);

	if (trigger.hasClass('modal-input-href')) {
		yes.attr('href', jQuery(trigger).attr('rev'));
	}
	if (trigger.hasClass('modal-input-click')) {
		yes.unbind('click').click(new Function(trigger.attr('rev'))).click(
				function() {
					trigger.data('overlay').close();
					return false;
				});
	}
	if (jQuery(selector).data('triggered')) {
		jQuery(selector).overlay().load();
	} else {
		jQuery(selector).data('triggered', true);
		jQuery(selector).overlay({
			mask : {
				color : '#aaa',
				loadSpeed : 'fast',
				opacity : 0.5
			},
			speed : 'fast',
			closeOnClick : true,
			load : true
		});
	}
}

function enableEditing() {
	$("form .identity").attr("readonly", true);
	$("form input").attr("readonly", false);
	$("form input:radio").attr("disabled", false);
	$("form input:checkbox").attr("disabled", false);
	$("form select").attr("disabled", false);
	$("form :button").hide();
	$("form :submit").show();
	$("form :reset").show();
	$("form .datepicker").datepicker();
};

function disableEditing() {
	$("form .identity").attr("readonly", true);
	$("form input").attr("readonly", true);
	$("form input:radio").attr("disabled", "disabled");
	$("form input:checkbox").attr("disabled", "disabled");
	$("form select").attr("disabled", "disabled");
	$("form :button").show();
	$("form :submit").hide();
	$("form :reset").hide();
	$("form .datepicker").datepicker("disable");
};

$("form .allow-edit").click(enableEditing);

$("form :reset").click(disableEditing);

if ($("form div .form-group").hasClass("has-error")) {
	enableEditing();
}

if (!$(".datepicker").prop("readonly")) {
	$(".datepicker").datepicker();
} else {
	$(".datepicker").datepicker("disable");
}

$(".row-selecting").click(function() {
	var $this = $(this);
	var id = $(this).attr("id")
	if ($this.is(':checked')) {
		console.log("checked " + id);
	} else {
		console.log("unchecked " + id);
	}
});