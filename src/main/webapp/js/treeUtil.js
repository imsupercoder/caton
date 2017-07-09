function showMenu(ipt) {
    var Input = $("#" + ipt);
    var InputOffset = $("#" + ipt).offset();
    $(".menuContent").css({
        left : InputOffset.left,
        top : InputOffset.top + Input.outerHeight()
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideTreeMenu() {
    $(".menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.class == "menuContent" || $(event.target).parents(".menuContent").length > 0)) {
        hideTreeMenu();
    }
}