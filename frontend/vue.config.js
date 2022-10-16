module.exports = {
	devServer: {
		open: true,
		host: "localhost",
		port: 8081,
		proxy: {
			"/api": {
				target: "http://localhost:8080/api",
				ws: true,
				changeOrigin: false,
				secure: true,
				pathRewrite: {
					"^/api": "",
				},
			},
		},
	},
};
