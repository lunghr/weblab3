window.onload = function () {
    updateClock();
    setInterval(updateClock, 11 * 1000)
}

function updateClock() {
    clock();
    circleClock();

}


function clock() {
    let now = new Date();
    let timeString = [now.getHours(), now.getMinutes(), now.getSeconds() < 10 ? ("0" + now.getSeconds()) : now.getSeconds()].join(':');
    let dateString = [now.getDate(), now.getMonth() + 1, now.getFullYear()].join('.');

    document.getElementById('clock').innerHTML = timeString;
    document.getElementById('data').innerHTML = dateString;

    // setTimeout(updateClock, 1000);
}

