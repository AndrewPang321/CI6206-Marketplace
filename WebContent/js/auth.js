// *** JavaScript file for login.js **

function logout() {
    $.get("logout", function(data, statusText, xhr) {
        if(xhr.status == 200) {
            window.location.replace("index.html");
        }
    });
}

function loginLogoutToggle() {
    $.get("login", function(data) {
        if (data.currentUserId != -1) {
            $("#loginNav").css("display", "none");
            $("#logoutNav").css("display", "block");
        } else {
            $("#loginNav").css("display", "block");
            $("#logoutNav").css("display", "none");
        }
    });
}

// function loginLogoutToggle() {
//     var currentUserObj = getCurrentUser();
//
// }