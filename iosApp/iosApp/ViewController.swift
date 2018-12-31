import UIKit
import app

class ViewController: UIViewController, PhotoView {
    
    var isUpdating: Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        label.text = Proxy().proxyHello()
    }
    
    func showError(error: KotlinThrowable) {
        <#code#>
    }
    
    func onUpdate(data: PhotoResponse) {
        <#code#>
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    @IBOutlet weak var label: UILabel!
}
