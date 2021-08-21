interface ISingleRepo {
    name: string;

}
// khai báo phương thức get
function httpGet(url: string) : Promise<Array<ISingleRepo>> {
    return new Promise(function (resolve, reject) {
        const request = new XMLHttpRequest();
        request.open("GET", url);
        request.send();
        request.onload = function () {
            if (this.status === 200) {
                //thành công
                resolve(this.response);
            } else {
                //something went wrong
                reject(new Error(this.statusText));
            }

        };
        request.onerror = function () {
            reject(new Error("XMLHttpRequest Error: " + this.statusText))

        };
    });
}

httpGet("https://api.github.com/search/repositories?q=angular")
    .then(function (value) {
        document.getElementById("content").innerText = value.toString();
    })
    .catch(function (reason) {
        console.error("something went wrong", reason);
    });


