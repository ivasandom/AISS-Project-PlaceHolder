var tree = {};

function addnode(obj) {
    var splitpath = obj.path.replace(/^\/|\/$/g, "").split('/');
    var ptr = tree;
    for (i = 0; i < splitpath.length; i++) {
        node = {
            text: splitpath[i],
        };
        if (i == splitpath.length - 1) {
            node.size = obj.size;
            node.type = obj.type;
            node.url = obj.url;
        }
        ptr[splitpath[i]] = ptr[splitpath[i]] || node;
        ptr[splitpath[i]].children = ptr[splitpath[i]].children || {};
        ptr = ptr[splitpath[i]].children;
    }
}


function loadFile(url, type) {
    if (type == 'blob') {
        // alert(url);
        $.get(url, function(data, status){
            require(["vs/editor/editor.main"], function () {
                var originalModel = monaco.editor.createModel(atob(data.content), "java");
                window.editor.setModel(originalModel);
                editor.addListener("didType", () => {
                    console.log(editor.getValue());
                });
            });
        });
    }
}

function transformChildren(obj) {
    var lista = [];
    for (var key in obj) {
        let x = obj[key];
        var url = obj[key]["url"];
        var type = obj[key]["type"];
        obj[key]['li_attr'] = {
            "onclick": "loadFile('" + url + "', '" + type + "');",
        }
        x.children = transformChildren(x.children);
        lista.push(x);
    }
    return lista;
}

function loadRepositoryTree(response) {
	response[0].tree.map(addnode);
	var fileExplorer = [];
	for (var key in tree) {
	    let x = tree[key];
	    var url = tree[key]["url"];
	    var type = tree[key]["type"];
	    tree[key]['li_attr'] = {
	        "onclick": "loadFile('" + url + "', '" + type + "');",
	    }
	    x.children = transformChildren(x.children);
	    fileExplorer.push(x);
	}
	
	$('#fileTree').jstree({
		'core': {
			'data': fileExplorer
		}
	});
}
