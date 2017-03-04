function getCookie(e) {
    for (var n = e + "=", t = document.cookie.split(";"), i = 0; i < t.length; i++) {
        for (var o = t[i];
                " " === o.charAt(0); )
            o = o.substring(1);
        if (0 === o.indexOf(n))
            return o.substring(n.length, o.length)
    }
    return ""
}
function setCookie(e, n, t) {
    var i = new Date;
    i.setTime(i.getTime() + 60 * t * 60 * 1e3);
    var o = "expires=" + i.toUTCString();
    document.cookie = e + "=" + n + "; " + o
}
function iOS() {
    var e = ["iPad Simulator", "iPhone Simulator", "iPod Simulator", "iPad", "iPhone", "iPod"];
    if (navigator.platform)
        for (; e.length; )
            if (navigator.platform === e.pop())
                return !0;
    return !1
}

function closePopupApp() {
    $(".popup-app").css("display", "none")
}

$(document).ready(function (e) {
    // check cookie
    var visited = getCookie('visited');
    console.log('visited:' + visited);

    if (visited === '') {
//        if (iOS()) {
//            $('.link-app').attr('href', 'http://m.onelink.me/907ff870');
//        } else {
//            $('.link-app').attr('href', 'http://m.onelink.me/2f25729d');
//        }
        $('.popup-app').css('display', 'block');
        setCookie('visited', '1', 24);
    }
});