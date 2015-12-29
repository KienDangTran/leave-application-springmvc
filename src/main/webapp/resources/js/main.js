$(document).ready(function() {
	$(".navbar").attr("data-offset-top", $("#header").height());

	$(".datepicker").datepicker({
	    dateFormat : "yy-mm-dd",
	    startDate : "-3d",
	    changeMonth : true,
	    changeYear : true,
	});

	$(".row-selecting").click(function() {
		var $this = $(this);
		var id = $(this).attr("id")
		if ($this.is(":checked")) {
			console.log("checked " + id);
		} else {
			console.log("unchecked " + id);
		}
	});

	$(".ajax-frm").submit(function(e) {
		e.preventDefault();
		var data = {};
		var frm = $(this);
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		// Gather Data also remove undefined keys(buttons)
		$.each(this, function(i, v) {
			var input = $(v);
			if (input.attr("type") == "radio" || input.attr("type") == "checkbox") {
				if (input.prop("checked")) {
					data[input.attr("name")] = input.val();
				}
			} else
				data[input.attr("name")] = input.val();
			delete data["undefined"];
		});

		$.ajax({
		    contentType : 'application/json; charset=utf-8',
		    type : frm.attr("method"),
		    dataType : "json",
		    data : JSON.stringify(data),
		    url : frm.attr("action"),
		    beforeSend : function(xhr) {
			    xhr.setRequestHeader(header, token);
		    },
		    success : function(response) {
			    if (response.status == "SUCCESS") {
				    result = "<div class='frm-notification alert alert-success'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>";
				    result += response.result;
				    result += "</strong></div>";
				    disableEditing(frm);
			    } else {
				    result = "<div class='frm-notification alert alert-danger'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a><strong>";
				    for (i = 0; i < response.result.length; i++) {
					    result += "<span class='control-label'>&#8226; " + response.result[i].code + "</span><br/>";
					    fieldName = response.result[i].field;
					    ele = $("#" + frm.attr("id") + " .form-group [name=" + fieldName + "]");
					    ele.parent().addClass("has-error")
				    }
				    result += "</strong></div>";
				    enableEditing(frm);
			    }
			    frm.append(result);
		    },
		    error : function(err) {
			    frm.html(err.responseText);
		    }
		});
	});

	if (!$(".datepicker").prop("readonly")) {
		$(".datepicker").datepicker();
		$(".datepicker").datepicker("enable");
	} else {
		$(".datepicker").datepicker("disable");
	}
});

function openConfirmationModal(selector) {
	var trigger = jQuery(selector);
	var yes = jQuery(trigger.attr('rel')).find('.btn-modal-yes').eq(0);

	if (trigger.hasClass('modal-input-href')) {
		yes.attr('href', jQuery(trigger).attr('rev'));
	}
	if (trigger.hasClass('modal-input-click')) {
		yes.unbind('click').click(new Function(trigger.attr('rev'))).click(function() {
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

function enableEditing(formSelector) {
	$(event.target).hide();
	$(formSelector).find("input").attr("readonly", false);
	$(formSelector).find("input:radio").attr("disabled", false);
	$(formSelector).find("input:checkbox").attr("disabled", false);
	$(formSelector).find("select").attr("disabled", false);
	$(formSelector).find(":submit").show();
	$(formSelector).find(":reset").show();
	$(formSelector).find(".datepicker").datepicker();
	$(formSelector).find(".datepicker").datepicker("enable");
	$(formSelector).find(".frm-notification").hide();
	$(formSelector).find(".identity").attr("readonly", true);
}

function disableEditing(formSelector) {
	$(formSelector).find("input").attr("readonly", true);
	$(formSelector).find("input:radio").attr("disabled", "disabled");
	$(formSelector).find("input:checkbox").attr("disabled", "disabled");
	$(formSelector).find("select").attr("disabled", "disabled");
	$(formSelector).find(":button").show();
	$(formSelector).find(":submit").hide();
	$(formSelector).find(":reset").hide();
	$(formSelector).find(".datepicker").datepicker("disable");
	$(formSelector).find(".frm-notification").hide();
	$(formSelector).find(".form-group").removeClass("has-error");
	$(formSelector).find(".identity").attr("readonly", true);
}