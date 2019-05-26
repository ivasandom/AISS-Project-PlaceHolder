require.config({
    paths: {
        vs: "https://unpkg.com/monaco-editor@0.8.3/min/vs"
    }
});

window.MonacoEnvironment = {
    getWorkerUrl: () => proxy
};
let proxy = URL.createObjectURL(
    new Blob(
        [
            `
            self.MonacoEnvironment = {
                baseUrl: 'https://unpkg.com/monaco-editor@0.8.3/min/'
            };
            importScripts('https://unpkg.com/monaco-editor@0.8.3/min/vs/base/worker/workerMain.js');
        `
        ], {
            type: "text/javascript"
        }
    )
);

require(["vs/editor/editor.main"], function () {
	let editor = monaco.editor.create(document.getElementById("editor"), {
        theme: "vs-dark"
    });

    window.editor = editor;
});

require(["vs/editor/editor.main"], function () {
 let diffEditor = monaco.editor.createDiffEditor(document.getElementById("diffEditor"), {
      theme: "vs-dark"
  });

  window.diffEditor = diffEditor;
  
  document.getElementById("diffEditor").style.display = "none";
});


// require(["vs/editor/editor.main"], function () {
//     var originalModel = monaco.editor.createModel("def hola():\n\tprint('Hola mundo')", "python");
//     window.editor.setModel(originalModel); 
// });