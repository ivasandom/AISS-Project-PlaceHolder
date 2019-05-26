/*
 * author: Jose Gamaza Diaz
 * Placeholder AISS
 */


function getJSONCommitChanges(){
	var changedFiles = codeEditor.getChangedFiles();
	var tree = [];
	var requestTreeContent;
	
	if (window.githost == "GitHub") {
		for (var url in changedFiles){
			let file = changedFiles[url];
			tree.push({
				path: file.tree.path,
				mode: file.tree.mode,
				type: file.type,
				content: file.newContent,
			})
		}
		requestTreeContent = {
				base_tree: window.baseTree,
				tree: tree
		}
		
	} else {
		
		for (var url in changedFiles){
			let file = changedFiles[url];
			tree.push({
				action: "update",
				file_path: file.tree.path,
				content: file.newContent,
			})
		}
		
		requestTreeContent = {
				branch: "master",
				force: true,
				commit_message: $("#commit-message").val(),
				tree: tree
		}		
		
	}
	
	return requestTreeContent;
	
}

class CodeEditor {
	constructor(){
		this.tabs = {};
        this.usedBlobs = {};
	}
	
	addTab(tabUrl, newTab){
		this.tabs[tabUrl] = newTab;
	}
	
	showDiffEditor(blobUrl){
		var blob = this.usedBlobs[blobUrl];
    	$("#diffEditor").css("display", "block");
		require(["vs/editor/editor.main"], function () {
    		
    		window.diffEditor.setModel({
				original: monaco.editor.createModel(blob.oldContent, 'java'),
				modified: monaco.editor.createModel(blob.newContent, 'java'),
			})

            window.diffEditor.currentBlob = blob;
    		window.diffEditor.addListener("didType", () => {
            	if (window.diffEditor.currentBlob == blob){
            		blob.updateContent(window.diffEditor.getValue());
            	}
            });
        });
	}
	
	openTab(tabUrl){
        if (!(tabUrl in this.tabs)) {
            this.tabs[tabUrl] = new Tab(tabUrl);
        }
        
        this.showBlobContent(tabUrl);
	}
	
	closeTab(tabElement, tabUrl){
		delete this.tabs[tabUrl];
		tabElement.remove();
	}
	
    showBlobContent(tabUrl) {
    	var blob = this.usedBlobs[tabUrl];
    	$("#diffEditor").css("display", "none");
    	require(["vs/editor/editor.main"], function () {
            var model = monaco.editor.createModel(blob.newContent, "java");
            window.editor.setModel(model);
            window.editor.currentBlob = blob;
            editor.addListener("didType", () => {
            	if (editor.currentBlob == blob){
            		blob.updateContent(editor.getValue());
            	}
            });
        });
    }

	getChangedFiles(){
		var result = {};
		for(var url in this.usedBlobs){
			let blob = this.usedBlobs[url];
			if (blob.isUpdated()){
				result[url] = blob;
			}
		}
		return result;
	}

    addUsedBlobs(url, blob){
        this.usedBlobs[url] = blob;
    }
}


class Tab {
	constructor(tabUrl){
		// base
		var blob = codeEditor.usedBlobs[tabUrl];
		
        this.blob = blob;
		this.text = blob.text;
        this.content = blob.newContent;
        this.render();
	}

    render() {
        $("#tabs").append("<li class='tab' data-bloburl='" + this.blob.url + "'><a>" + this.text + "</a></li>");
    }
}


class EditorBlob {
    constructor(options){
        this.text = options.text;
        this.tree = options.tree;
        this.url = options.url;
        this.type = options.type;
        this.mode = options.mode;
        this.path = options.path;
        this.oldContent = options.content;
        this.newContent = options.content;
    }

    static load(url) {
        
        if (!(url in codeEditor.usedBlobs)){
            $.get(url, function(data, status){
				// Response encoded in base64
            	var content = atob(data.content);
            	var tree = trees[url];
				var blob = new EditorBlob({
	                text: tree.text,
	                tree: tree,
	                url: url,
	                path: tree.path,
	                type: tree.type,
	                mode: tree.mode,
	                content: content,
	            });

	            codeEditor.addUsedBlobs(url, blob);

			});
        
        }

        codeEditor.openTab(url);
    }

    isUpdated() {
        return this.oldContent != this.newContent;
    }
    
    updateContent(newContent){
    	this.newContent = newContent;
    }

}


class Tree {
	constructor(options){
		this.size = options.size;
		this.url = options.url;
		this.type = options.type;
		this.text = options.text;
		this.mode = options.mode;
		this.path = options.path;
		
		this.oldContent = null;
		this.newContent = null;
	}
	
	static getTrees(responseTrees){
        var result = {};
        tree = {};
	    responseTrees.map(addnode);
	    var fileExplorer = [];
	    for (var key in tree) {
	        let x = tree[key];
	        var url = tree[key]["url"];
	        var type = tree[key]["type"];
	        
	        if (!url) {
	        	url = "https://gitlab.com/api/v4/projects/" + window.repositoryOwner + "%2F" + window.repositoryName +"/repository/blobs/" + x.id;
	        }
	        
	        if (!url.includes("access_token")) {
	        	url = url + "?access_token=" + window.githostAccessToken;
	        }
	        
	        if (type=="blob"){
                tree[key]['li_attr'] = {
                    "onclick": "EditorBlob.load('" + url + "');",
                }
            }

	        x.children = transformChildren(x.children, result);
	        fileExplorer.push(x);

	        
            result[x.url] = new Tree({
                size: x.size,
                url: url,
                type: x.type,
                text: x.text,
                path: x.path,
                mode: x.mode,
            });
	    }
        
        // Cargamos el explorador
        $('#fileTree').jstree({
		    'core': {
			    'data': fileExplorer
		    }
	    });

		return result;
	}
}




var tree = {};

function addnode(obj) {
    var splitpath = obj.path.replace(/^\/|\/$/g, "").split('/');
    var ptr = tree;
    for (i = 0; i < splitpath.length; i++) {
        node = {
            text: splitpath[i],
        };
        var url = obj.url;
        
        if (!url) {
        	url = "https://gitlab.com/api/v4/projects/" + window.repositoryOwner + "%2F" + window.repositoryName +"/repository/blobs/" + obj.id;
        }
        
        if (!url.includes("access_token")) {
        	url = url + "?access_token=" + window.githostAccessToken;
        }
        
        if (i == splitpath.length - 1) {
            node.size = obj.size;
            node.type = obj.type;
            node.url = url;
            node.path = obj.path;
            node.mode = obj.mode;
            node.id = obj.id;
        }
        ptr[splitpath[i]] = ptr[splitpath[i]] || node;
        ptr[splitpath[i]].children = ptr[splitpath[i]].children || {};
        ptr = ptr[splitpath[i]].children;
    }
}


function transformChildren(obj, result) {
    var lista = [];
    for (var key in obj) {
        let x = obj[key];
        var url = obj[key]["url"];
        var type = obj[key]["type"];
        
        if (!url) {
        	url = "https://gitlab.com/api/v4/projects/" + window.repositoryOwner + "%2F" + window.repositoryName +"/repository/blobs/" + x.id;
        }
        
        if (!url.includes("access_token")) {
        	url = url + "?access_token=" + window.githostAccessToken;
        }
        
        if (type=="blob"){
            obj[key]['li_attr'] = {
                "onclick": "EditorBlob.load('" + url + "');",
            };    
        }       
        
        result[url] = new Tree({
            size: x.size,
            url: url,
            type: x.type,
            text: x.text,
            path: x.path,
            mode: x.mode,
        });
        
        x.children = transformChildren(x.children, result);
        lista.push(x);
    }
    return lista;
}
