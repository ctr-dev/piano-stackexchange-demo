module.exports = {
    devServer: {
        proxy: {
            '/stack-exchange': {
                target: 'http://localhost:9101',
                changeOrigin: true
            }
        }
    },
    outputDir: '../middleware/build/resources/main/public'
}
