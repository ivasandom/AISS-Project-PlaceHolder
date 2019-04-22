var min = 300;
var max = 3600;
var mainmin = 200;

$('#split-bar').mousedown(function (e) {
    e.preventDefault();
    $(document).mousemove(function (e) {
        e.preventDefault();
        var x = e.pageX - $('#sidebar').offset().left;
        if (x > min && x < max && e.pageX < ($(window).width() - mainmin)) {  
          $('#sidebar').css("width", x);
          $('#editor').css("margin-left", x + 60);
          // bugeado
          $('#editor').css("width", $(window).width() - $('#sidebar').width());
          $('.monaco-editor').css("width", $(window).width() - $('#sidebar').width());
        }
    })
});
$(document).mouseup(function (e) {
    $(document).unbind('mousemove');
});

$()