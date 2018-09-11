// *** JavaScript file for common utils ***

/* 
 * To show alert box with parameter as JSON:
 * { message: "displayText", class: "alertClassFromBootstrap" }
 */
function showAlert(alertObj) {
    var html = `<div class="alert alert-${alertObj.class} alert-dismissible fade" role="alert" id="alert">`;
    html += `${alertObj.message}`;
    html += `<button type="button" class="close" data-dismiss="alert" aria-label="Close">`;
    html += `<span aria-hidden="true">&times;</span>`;
    html += `</button>`;
    html += `</div>`;

    $("#alertBox").append(html);
    // The alert lasts for 2s
    $("#alert").fadeTo(2000, 500).slideUp(500, () => {
        $("#success-alert").slideUp(500);
        $("#alert").alert("close");
    });
}
