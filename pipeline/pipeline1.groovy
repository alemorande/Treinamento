if (params.Continuar != 'Sim') {
	currentBuild.result = 'UNSTABLE'
} else {
	node {
        def nomeEstagio = ''
		def seqEstagio = 1

        ansiColor('xterm') {
            try {
                nomeEstagio = 'Estagio ' + seqEstagio++
                stage(nomeEstagio) {
                    echo 'Iniciando ' + nomeEstagio
                    writeFile file:nomeEstagio + '.txt', text:nomeEstagio, encoding:'UTF-8'
                    echo nomeEstagio + ' Concluido'
                }
                nomeEstagio = 'Estagio ' + seqEstagio++
                stage(nomeEstagio) {
                    echo 'Iniciando ' + nomeEstagio
                    writeFile file:nomeEstagio + '.txt', text:nomeEstagio, encoding:'UTF-8'
                    parallel (
                        (nomeEstagio + '.1'): {
                            writeFile file:nomeEstagio + '.1.txt', text:nomeEstagio + '.1', encoding:'UTF-8'
                        }, (nomeEstagio + '.2'): {
                            writeFile file:nomeEstagio + '.2.txt', text:nomeEstagio + '.2', encoding:'UTF-8'
                        }, (nomeEstagio + '.3'): {
                            writeFile file:nomeEstagio + '.3.txt', text:nomeEstagio + '.3', encoding:'UTF-8'
                        }
                    )
                    echo nomeEstagio + ' Concluido'
                }
                nomeEstagio = 'Estagio ' + seqEstagio++
                stage(nomeEstagio) {
                    echo 'Iniciando ' + nomeEstagio
                    writeFile file:nomeEstagio + '.txt', text:nomeEstagio, encoding:'UTF-8'
                    echo nomeEstagio + ' Concluido'
                }
            } catch (error) {
                echo '\n\n\033[1;31m[Erro]\033[0m Erro no ' + nomeEstagio + '\n\n'
                echo error.toString()
                currentBuild.result = 'FAILURE'
            }
			archiveArtifacts '*.txt'
        }
	}
}
