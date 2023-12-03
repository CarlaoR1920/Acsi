PUBLIC_KEY_PATH = "~/.ssh/id_rsa.pub"
READ_PUBLIC_KEY = File.read(File.expand_path(PUBLIC_KEY_PATH)).strip

VM_IP = "192.168.56.10"
PRIVATE_KEY_PATH = "~/.ssh/id_rsa"

Vagrant.configure("2") do |config|
  config.vm.box = "bento/ubuntu-20.04"
  
  config.vm.provider "virtualbox" do |vb|
    vb.memory = "1024"
    vb.cpus = 2
  end
  
  config.vm.define "ACSI-VM" do |node|
    node.vm.hostname = "ACSI-VM"
    node.vm.network :private_network, ip: VM_IP

    if File.exist?(File.expand_path(PRIVATE_KEY_PATH))
      node.vm.provision "file", source: PRIVATE_KEY_PATH, destination: "~/.ssh/"
    end

    if File.exist?(File.expand_path("~/.gitconfig"))
      node.vm.provision "file", source: "~/.gitconfig", destination: "~/"
    end

    if File.exist?(File.expand_path("~/.git-credentials"))
      node.vm.provision "file", source: "~/.git-credentials", destination: "~/"
    end
  end

  config.vm.provision "shell", inline: <<-SHELL
    echo "#{READ_PUBLIC_KEY}" >> .ssh/authorized_keys
    sed -i 's/#PubkeyAuthentication yes/PubkeyAuthentication yes/g' /etc/ssh/sshd_config
    sed -i 's/#PasswordAuthentication yes/PasswordAuthentication no/g' /etc/ssh/sshd_config
    systemctl restart sshd

    apt-get update
    apt-get install -y vim ruby-github-linguist zip unzip
    snap install task --classic

    # Install 'git subrepo' command
    git clone https://github.com/ingydotnet/git-subrepo.git .git-subrepo/
    echo -e "\n# Add 'git subrepo' command .rc file" >> .bashrc
    echo "source ~/.git-subrepo/.rc" >> .bashrc

    # Install Docker Engine
    apt-get install -y ca-certificates curl gnupg
    install -m 0755 -d /etc/apt/keyrings
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | gpg --dearmor -o /etc/apt/keyrings/docker.gpg
    chmod a+r /etc/apt/keyrings/docker.gpg

    echo \
      "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
      "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | \
      tee /etc/apt/sources.list.d/docker.list > /dev/null
    apt-get update

    apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

    # Run 'docker' command without 'sudo'
    usermod -aG docker vagrant
    newgrp docker
  SHELL
end
