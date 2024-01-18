var s_icon = document.querySelector('.search-icon')
var s_bar = document.querySelector('.search-bar')
s_icon.onclick = function() {
    s_icon.classList.toggle('search-icon-active')
    var active = document.querySelector('.search-icon-active')
    if(active) {
        s_bar.classList.add('dp-block')

    }
    else {
        s_bar.classList.remove('dp-block')
    }
}