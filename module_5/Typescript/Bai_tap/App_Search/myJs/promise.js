console.log("line 1 ....");
console.log("line 2.....");
const p = new Promise(function (resolve, reject) {
    setTimeout(() => {
        if (true) {
            resolve("line 3.....");
        }
        else {
            reject("có lỗi xảy ra !!!");
        }
    }, 3000);
});
p.then((value) => console.log(value)).catch((value) => console.log(value));
console.log("line 4....");
