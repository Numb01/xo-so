<%-- 
    Document   : header
    Created on : Mar 4, 2016, 1:59:33 PM
    Author     : hanhlm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title}</title>
<meta name="keywords" content="${keyword}"/>
<meta name="news_keywords" content="${keyword}"/>
<meta name="description" content="${des}"/>
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='pragma' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta content="index, follow" name="robots"/>
<meta http-equiv="content-language" content="vi"/>
<meta content="SXMB" name="author"/>
<meta property="og:type" content="website" />
<meta name="google-site-verification" content="H1eO6H9qnT93VXAnGWSzRIEkcOgPjDe45ccm-XvLRQY" />
<meta property="og:title" content="${title}" />
<meta property="og:description" content="${des}" />  
<meta property="og:url" content="${canonical}" />
<meta property="og:site_name" content="XSMB - Chuyên trang kết quả xổ số miền bắc lớn nhất Việt Nam" />
<meta name="viewport" content="user-scalable=yes, width=device-width, initial-scale=1.0, maximum-scale=1">
<meta name="google-site-verification" content="Ywlgj6iOiP0dSsvYc1ldi0Fr9PfV5AJIbSDttjeV77Q">
<meta property="og:image" content="http://sxmb.vn/images/xsmb-logo.png" />
<link rel="canonical" href="http://sxmb.vn/ "/>
<link rel="shortcut icon" href="/images/favi.png">
<link href="/css/mobile/style.css" rel="stylesheet" type="text/css" />
<link href="/css/mobile/Rep.css" rel="stylesheet" type="text/css" />
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700&subset=vietnamese,latin, latin-ext' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700&subset=vietnamese,latin,latin-ext" />
<link href="/css/mobile/datepicker.css" rel="stylesheet" />
<script src="/js/jquery-1.7.min.js"></script>
<script src="/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="/js/jquery.ui.datepicker-fr.js" ></script>
<script src="/js/mobi.js"></script>
<script src="/js/xsmb.js"></script>
<!--<script src="/js/popupMixi.js"></script>-->
<script src="http://ole.vn:8081/themes/classic/js/popup_show.js"></script>
<script type="text/javascript">
    $(function () {
        $('#datepicker').click(function () {
            $('body').append('<div  class="modal-open" style="display: block;"></div>');
            $('.modal-open').click(function () {
                $('.modal-open').remove();
            });
        });

        $('#datepicker').datepicker({
            inline: true,
            showOtherMonths: true,
            dayNamesMin: ['CN', 'T.2', 'T.3', 'T.4', 'T.5', 'T.6', 'T.7'],
            onSelect: function (date) {
                var url = document.URL;
                date = date.replace(/\//g, "-");
                //alert(date);
                if (date !== null && date !== "") {
                    var arrDate = date.split("-");
                    var yyyy = arrDate[2];
                    var mm = arrDate[1];
                    var dd = arrDate[0];
                    var d = new Date();
                    if (eval(yyyy) === eval(d.getUTCFullYear())) {
                        if (eval(mm) === eval(d.getMonth() + 1)) {
                            if (eval(dd) <= eval(d.getDate())) {
                                if (url.indexOf('xsmt') > -1) {
                                    url = "/xsmt-xo-so-mien-trung-ngay-" + date + ".html";
                                    window.location.href = url;
                                } else if (url.indexOf('xsmn') > -1) {
                                    url = "/xsmn-xo-so-mien-nam-ngay-" + date + ".html";
                                    window.location.href = url;
                                } else if (url.indexOf('xsmb') > -1 || url.indexOf('xo-so-mien-bac-ngay') > -1 || url.localeCompare('http://sxmb.vn') === 0 || url.localeCompare('http://sxmb.vn/') === 0 || url.localeCompare('http://localhost:8080/') === 0) {
                                    url = "/xsmb-xo-so-mien-bac-ngay-" + date + ".html";
                                    window.location.href = url;
                                } else {
                                    if (url.indexOf('-ngay-') > -1) {
                                        var urlTemp = url.substring(url.indexOf('/', 7), url.length);
                                        var urlTemp2 = urlTemp.replace('.html', '');
                                        url = urlTemp2.substring(0, urlTemp2.indexOf('-ngay-')) + "-ngay-" + date + ".html";
                                        window.location.href = url;
                                    } else {
//                                        var urlTemp = url.substring(url.indexOf('/', 7), url.length);
//                                        var urlTemp2 = urlTemp.replace('.html', '');
//                                        url = urlTemp2 + "-ngay-" + date + ".html";
                                        url = "/xsmb-xo-so-mien-bac" + "-ngay-" + date + ".html";
                                        window.location.href = url;
                                    }
                                }
                            } else {
                                window.location.href = url;
                            }
                        } else if (eval(mm) < eval(d.getMonth() + 1)) {
                            if (url.indexOf('xsmt') > -1) {
                                url = "/xsmt-xo-so-mien-trung-ngay-" + date + ".html";
                                window.location.href = url;
                            } else if (url.indexOf('xsmn') > -1) {
                                url = "/xsmn-xo-so-mien-nam-ngay-" + date + ".html";
                                window.location.href = url;
                            } else if (url.indexOf('xstd') > -1 || url.indexOf('xo-so-mien-bac-ngay') > -1 || url.localeCompare('http://xsmb.vn') === 0 || url.localeCompare('http://xsmb.vn/') === 0) {
                                url = "/xsmb-xo-so-mien-bac-ngay-" + date + ".html";
                                window.location.href = url;
                            } else {
                                if (url.indexOf('-ngay-') > -1) {
                                    var urlTemp = url.substring(url.indexOf('/', 7), url.length);
                                    var urlTemp2 = urlTemp.replace('.html', '');
                                    url = urlTemp2.substring(0, urlTemp2.indexOf('-ngay-')) + "-ngay-" + date + ".html";
                                    window.location.href = url;

                                } else {
                                    url = "/xsmb-xo-so-mien-bac" + "-ngay-" + date + ".html";
                                    window.location.href = url;
                                }
                            }
                        }
                    }
                    if (eval(yyyy) < eval(d.getUTCFullYear())) {//neu nam nho hon nam hien tai
                        if (url.indexOf('xsmt') > -1) {
                            //mien trung
                            url = "/xsmt-xo-so-mien-trung-ngay-" + date + ".html";
                            window.location.href = url;
                        } else if (url.indexOf('xsmn') > -1) {
                            //mien nam
                            url = "/xsmn-xo-so-mien-nam-ngay-" + date + ".html";
                            window.location.href = url;
                        } else if (url.indexOf('xsmb') > -1 || url.indexOf('xo-so-mien-bac-ngay') > -1 || url.localeCompare('http://sxmb.vn') === 0 || url.localeCompare('http://sxmb.vn/') === 0 || url.localeCompare('http://localhost:8080/') === 0) {
                            url = "/xsmb-xo-so-mien-bac-ngay-" + date + ".html";
                            window.location.href = url;
                        } else {
                            if (url.indexOf('-ngay-') > -1) {
                                var urlTemp = url.substring(url.indexOf('/', 7), url.length);
                                var urlTemp2 = urlTemp.replace('.html', '');
                                url = urlTemp2.substring(0, urlTemp2.indexOf('-ngay-')) + "-ngay-" + date + ".html";
                                window.location.href = url;

                            } else {
                                url = "/xsmb-xo-so-mien-bac" + "-ngay-" + date + ".html";
                                window.location.href = url;
                            }
                        }
                    }
                } else {
                    window.location.href = url;
                }

            }
        });

    });
</script>
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-84165420-1', 'auto');
    ga('send', 'pageview');

</script>